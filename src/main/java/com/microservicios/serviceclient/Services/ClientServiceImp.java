package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Feign.PhotoFeign;
import com.microservicios.serviceclient.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    ClientRepository repository;
    @Autowired
    PhotoConvertService servicePhotoConvertService;
    @Autowired
    PhotoFeign photoFeign;
    @Override
    public List<ClientDTO> clients(int age) {
        List<ClientDTO> clientsDTO=repository.clients(age);
        List<ClientPK> clientPKS=this.getPks(clientsDTO);
        List<PhotoDTO> photosDTO=photoFeign.photosById(clientPKS).getBody();
        return servicePhotoConvertService.photosToClients(clientsDTO,photosDTO);
    }

    @Override
    public ClientDTO clientById(int numberId, String typeId) {
        PhotoDTO photoDTO=photoFeign.photoById(numberId,typeId).getBody();
        ClientDTO clientDTO=repository.clientById(numberId,typeId);
        return servicePhotoConvertService.photoToClient(clientDTO,photoDTO);
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        PhotoDTO photoDTO= servicePhotoConvertService.clientToPhoto(clientDTO);
        PhotoDTO photoResult=photoFeign.savePhoto(photoDTO).getBody();
        ClientDTO clientDTOResult=repository.saveClient(clientDTO);
        return servicePhotoConvertService.photoToClient(clientDTOResult,photoResult);
    }

    @Override
    public boolean deleteClient(int numberId, String typeId) {
        String result=photoFeign.deleteById(numberId,typeId).getBody();
        return repository.deleteClient(numberId,typeId);
    }

    public List<ClientPK> getPks(List<ClientDTO> clientDTOS){
        List<ClientPK> clientPKS=new ArrayList<>();
        for (ClientDTO clientDTO:clientDTOS) {
            ClientPK clientPK=ClientPK.builder()
                    .number_id(clientDTO.getNumber_id())
                    .type_id(clientDTO.getType_id())
                    .build();
            clientPKS.add(clientPK);
        }
        return  clientPKS;
    }
}
