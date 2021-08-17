package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Feign.PhotoFeign;
import com.microservicios.serviceclient.Mappers.ClientMapper;
import com.microservicios.serviceclient.Mappers.PhotoMapper;
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
    PhotoFeign photoFeign;
    @Override
    public List<ClientDTO> clients(int age) {
        List<ClientRepositoryDTO> clientsRepositoryDTO=repository.clients(age);
        List<String> clientPKS=this.getPks(clientsRepositoryDTO);
        List<PhotoDTO> photosDTO=photoFeign.photosById(clientPKS).getBody();
        List<ClientDTO> clientsDTOResult=new ArrayList<>();
        for (ClientRepositoryDTO clientRepositoryDTO:clientsRepositoryDTO) {
            PhotoDTO photoDTO=photosDTO.stream()
                    .filter(photo -> photo.getId().toString().equals(clientRepositoryDTO.getId_photo()))
                    .findFirst()
                    .orElse(null);
            ClientDTO clientDTO= ClientMapper.INSTANCE.toClientDTO(clientRepositoryDTO,photoDTO);
            clientsDTOResult.add(clientDTO);
        }
        return clientsDTOResult;
    }

    @Override
    public ClientDTO clientById(int numberId, String typeId) {
        ClientRepositoryDTO clientRepositoryDTO=repository.clientById(numberId,typeId);
        if(clientRepositoryDTO!=null){
            PhotoDTO photoDTO=photoFeign.photoById(clientRepositoryDTO.getId_photo()).getBody();
            return ClientMapper.INSTANCE.toClientDTO(clientRepositoryDTO,photoDTO);
        }
        return null;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        String idPhoto=repository.getIdPhoto(clientDTO.getNumber_id(),clientDTO.getType_id());
        PhotoDTO photoDTO= PhotoMapper.INSTANCE.clientToPhoto(clientDTO,idPhoto);
        PhotoDTO photoResult=photoFeign.savePhoto(photoDTO).getBody();
        if(photoResult!=null){
            ClientRepositoryDTO clientRepositoryDTO= ClientMapper.INSTANCE.toClientRepositoryDTO(clientDTO,photoResult);
            repository.saveClient(clientRepositoryDTO);
            return clientDTO;
        }
        return null;
    }

    @Override
    public boolean deleteClient(int numberId, String typeId) {
        ClientRepositoryDTO clientRepositoryDTO=repository.clientById(numberId,typeId);
        if(clientRepositoryDTO!=null){
            String idPhoto=clientRepositoryDTO.getId_photo();
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

    public List<String> getPks(List<ClientRepositoryDTO> clientsRepositoryDTO){
        List<String> clientPKS=new ArrayList<>();
        for (ClientRepositoryDTO clientRepositoryDTO:clientsRepositoryDTO) {
            clientPKS.add(clientRepositoryDTO.getId_photo());
        }
        return  clientPKS;
    }
}
