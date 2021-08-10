package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Feign.PhotoFeign;
import com.microservicios.serviceclient.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    ClientRepository repository;
    @Autowired
    PhotoConvertService photoConvertService;
    @Autowired
    PhotoFeign photoFeign;
    @Override
    public List<ClientDTO> clients(int age) {
        List<ClientDTO> clientsDTO=repository.clients(age);
        List<String> clientPKS=this.getPks(clientsDTO);
        List<PhotoDTO> photosDTO=photoFeign.photosById(clientPKS).getBody();
        return photoConvertService.photosToClients(clientsDTO,photosDTO);
    }

    @Override
    public ClientDTO clientById(int numberId, String typeId) {
        ClientDTO clientDTO=repository.clientById(numberId,typeId);
        if(clientDTO!=null){
            if(clientDTO.getPhoto()!=null){
                PhotoDTO photoDTO=photoFeign.photoById(clientDTO.getPhoto()).getBody();
                return photoConvertService.photoToClient(clientDTO,photoDTO,false);
            }
            return clientDTO;
        }
        return null;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        String idPhoto=repository.getIdPhoto(clientDTO.getNumber_id(),clientDTO.getType_id());
        PhotoDTO photoDTO= photoConvertService.clientToPhoto(clientDTO,idPhoto);
        PhotoDTO photoResult=photoFeign.savePhoto(photoDTO).getBody();
        if(photoResult!=null){
           clientDTO=photoConvertService.photoToClient(clientDTO,photoResult,true);
            ClientDTO clientDTOResult=repository.saveClient(clientDTO);
            return photoConvertService.photoToClient(clientDTOResult,photoResult,false);
        }
        return null;
    }

    @Override
    public boolean deleteClient(int numberId, String typeId) {
        ClientDTO clientDTO=repository.clientById(numberId,typeId);
        if(clientDTO!=null){
            String idPhoto=clientDTO.getPhoto();
            String result=null;
            if(idPhoto!=null){
                result=photoFeign.deleteById(idPhoto).getBody();
            }
            if(result==null || !result.equals("removed")){
                return false;
            }
            return repository.deleteClient(numberId,typeId);
        }
        return false;

    }

    public List<String> getPks(List<ClientDTO> clientDTOS){
        List<String> clientPKS=new ArrayList<>();
        for (ClientDTO clientDTO:clientDTOS) {
            clientPKS.add(clientDTO.getPhoto());
        }
        return  clientPKS;
    }
}
