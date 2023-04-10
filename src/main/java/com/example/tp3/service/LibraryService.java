package com.example.tp3.service;

import com.example.tp3.dtos.ClientDTO;
import com.example.tp3.dtos.LivreDTO;
import com.example.tp3.models.library.*;
import com.example.tp3.models.users.Client;
import com.example.tp3.repository.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class LibraryService {

    //Repositories

    private final ClientRepository clientRepository;
    private final DocumentRepository documentRepository;
    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;
    private final MediaRepository mediaRepository;

    public LibraryService(ClientRepository clientRepository, DocumentRepository documentRepository, EmpruntRepository empruntRepository, LivreRepository livreRepository, MediaRepository mediaRepository) {
        this.clientRepository = clientRepository;
        this.documentRepository = documentRepository;
        this.empruntRepository = empruntRepository;
        this.livreRepository = livreRepository;
        this.mediaRepository = mediaRepository;
    }
    //Library
    public Livre saveLivre(String title, String author, String editor, long exemplaires, int releaseYear, int nbPages, String genre){
        return livreRepository.save(Livre.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).nbPages(nbPages).genre(genre).build());
    }


    //Client
    public Client saveClient(String name, String address){
        return clientRepository.save(Client.builder().clientName(name).clientAddress(address).build());
    }

    public List<ClientDTO> getClients(){
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> dtos = new ArrayList<>();
        for(Client c  : clients){
            ClientDTO dto = ClientDTO.builder().clientID(c.getClientID()).clientName(c.getClientName()).clientAddress(c.getClientAddress()).build();
            dtos.add(dto);
        }
        return dtos;
    }


    public void payDebts(long clientId){
        Client c = clientRepository.findByIdWithFines(clientId);
        for(Dette d : c.getDettes()){
            Emprunt e = d.getEmpruntEndette();
            d.setEmpruntEndette(null);
            c.getEmprunts().remove(e);
        }
        c.getDettes().clear();
        clientRepository.save(c);
        System.out.println("Dettes payées");
    }


    @Transactional
    public void borrowDocument(long clientId, long documentId) throws  Exception{
        Document document = documentRepository.findById(documentId).get();
        Client client = clientRepository.findById(clientId).get();
        client.setDettes(clientRepository.findByIdWithFines(clientId).getDettes());

        if(document.getExemplaires() < 1){throw new Exception("Erreur");}
        if(client.isHasDebt()){
            throw new Exception("Error");
        }
        for(Emprunt e : client.getEmprunts()){
            if(e.getDoc() == document){
                throw new Exception("Document déjà dans votre liste d'emprunt");
            }
        }
        document.setExemplaires(document.getExemplaires() - 1);
        documentRepository.save(document);
        Emprunt emprunt = Emprunt.builder().doc(document).client(client).dateDeRetour(LocalDate.now().minusDays(1)).build();
        client.getEmprunts().add(emprunt);
        clientRepository.save(client);
    }

    public void returnDocument(long bookId, long clientId) {
        final Document d = documentRepository.findById(bookId).get();
        d.setExemplaires(d.getExemplaires() + 1);
        final Emprunt e = empruntRepository.getWithClientIdAndBookId(bookId,clientId);
        final Client c = clientRepository.findByIdWithFines(clientId);
        final Dette dette = checkDettes(e);
        if(dette != null){
            c.getDettes().add(dette);
            clientRepository.save(c);
        }
        clientRepository.save(c);
    }

    public Dette checkDettes(Emprunt e){
        if(e.getDateDeRetour().isBefore(LocalDate.now())){
            double cout = e.getDateDeRetour().until(LocalDate.now(), ChronoUnit.DAYS);
            Dette dette = Dette.builder().amende(cout * 0.25).client(e.getClient()).build();
            dette.setEmpruntEndette(e);
            return dette;
        }
        return null;
    }

    public List<Emprunt> getEmprunts(long clientId){
        return empruntRepository.findEmprunts(clientId);
    }

    public Client saveClient(Client toClient) {
        return clientRepository.save(toClient);
    }

    public void saveLivre(Livre livre) {
        livreRepository.save(livre);
    }

    public List<LivreDTO> getLivres() {
        List<Livre> livres = livreRepository.findAll();
        List<LivreDTO> livreDTOS = new ArrayList<>();
        for (Livre l : livres){
            LivreDTO dto = LivreDTO.builder().author(l.getAuthor()).genre(l.getGenre()).editor(l.getEditor())
                    .title(l.getTitle()).nbPages(l.getNbPages()).exemplaires(l.getExemplaires()).releaseYear(l.getReleaseYear()).build();
            livreDTOS.add(dto);
        }
        return livreDTOS;
    }

    public List<Media> getMedias() {
        return mediaRepository.findAll();
    }

    public void saveMedia(Media media) {
        mediaRepository.save(media);
    }

    public Optional<Client> findClientWithId(long id) {
        return clientRepository.findById(id);
    }

    public Optional<Document> findDocumentWithId(long id) {
        return documentRepository.findById(id);
    }

    public Client findByIdWithAll(long id) {
        return clientRepository.findByIdWithFines(id);
    }

    public Document findDocumentWithTitleTop(String title) { return documentRepository.findDocumentWithTitle(title).get(0);

    }
    public Set<Dette> getDettesWithClientId(long id){
        return clientRepository.findByIdWithFines(id).getDettes();
    }
}
