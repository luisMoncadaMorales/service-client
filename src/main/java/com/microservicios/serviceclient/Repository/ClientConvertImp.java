package com.microservicios.serviceclient.Repository;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClientConvertImp implements ClientConvert {
    @Override
    public ClientRepositoryDTO clientToDTO(Client client) {
        ClientRepositoryDTO clientRepositoryDTO= ClientRepositoryDTO.builder()
                .number_id(client.getClientPk().getNumber_id())
                .type_id(client.getClientPk().getType_id())
                .name(client.getName())
                .last_name(client.getLast_name())
                .age(client.getAge())
                .city(client.getCity())
                .id_photo(client.getId_photo())
                .build();
        return clientRepositoryDTO;
    }

    @Override
    public Client DTOToClient(ClientRepositoryDTO clientRepositoryDTO) {
        ClientPK clientPK= ClientPK.builder()
                .number_id(clientRepositoryDTO.getNumber_id())
                .type_id(clientRepositoryDTO.getType_id()).build();
        Client client= Client.builder()
                .clientPk(clientPK)
                .name(clientRepositoryDTO.getName())
                .last_name(clientRepositoryDTO.getLast_name())
                .age(clientRepositoryDTO.getAge())
                .city(clientRepositoryDTO.getCity())
                .id_photo(clientRepositoryDTO.getId_photo())
                .build();
        return client;
    }

    @Override
    public List<ClientRepositoryDTO> listClientToDTO(List<Client> clients) {
        ArrayList<ClientRepositoryDTO> clientRepositoryDTOS= new ArrayList<>();
        for (Client client: clients) {
            ClientRepositoryDTO clientRepositoryDTO=this.clientToDTO(client);
            clientRepositoryDTOS.add(clientRepositoryDTO);
        }
        return clientRepositoryDTOS;
    }

    @Override
    public List<Client> listDTOToClient(List<ClientRepositoryDTO> clientsRepositoryDTO) {
        ArrayList<Client> clients= new ArrayList<>();
        for (ClientRepositoryDTO clientRepositoryDTO: clientsRepositoryDTO) {
            clients.add(this.DTOToClient(clientRepositoryDTO));
        }
        return clients;
    }
}
