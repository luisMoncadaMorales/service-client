package com.microservicios.serviceclient.Repository;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Persistence.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientRepositoryImp implements ClientRepository{
    @Autowired
    ClientDAO clientDAO;
    @Autowired
    ClientConvert clientConvert;
    @Override
    public List<ClientDTO> clients(int age) {
        List<Client> clients=clientDAO.clients(age);
        return clientConvert.listClientToDTO(clients);
    }

    @Override
    public ClientDTO clientById(int numberId, String typeId) {
        ClientPK clientPK= ClientPK.builder()
                .number_id(numberId)
                .type_id(typeId).build();
        Optional<Client> client=clientDAO.findById(clientPK);
        if(client.isPresent()) {
            return clientConvert.clientToDTO(client.get());
        }
        return null;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client=clientConvert.DTOToClient(clientDTO);
        try {
            Client clientResult= clientDAO.save(client);
            return clientConvert.clientToDTO(clientResult);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean deleteClient(int numberId, String typeId) {
        ClientPK clientPK= ClientPK.builder()
                .number_id(numberId)
                .type_id(typeId).build();
        try {
            clientDAO.deleteById(clientPK);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String getIdPhoto(int numberId, String typeId) {
        return clientDAO.getIdPhoto(numberId,typeId);
    }


}
