package com.example.tp3.service;

import com.example.tp3.models.library.Livre;
import com.example.tp3.models.library.Media;
import com.example.tp3.models.library.MediaType;
import com.example.tp3.models.users.Client;
import com.example.tp3.repository.*;

public class LibraryService {

    //Repositories
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final DetteRepository detteRepository;
    private final DocumentRepository documentRepository;
    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;
    private final MediaRepository mediaRepository;

    public LibraryService(AdminRepository adminRepository, ClientRepository clientRepository, DetteRepository detteRepository, DocumentRepository documentRepository, EmpruntRepository empruntRepository, LivreRepository livreRepository, MediaRepository mediaRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.detteRepository = detteRepository;
        this.documentRepository = documentRepository;
        this.empruntRepository = empruntRepository;
        this.livreRepository = livreRepository;
        this.mediaRepository = mediaRepository;
    }
    //Library
    public Livre saveLivre(String title, String author, String editor, long exemplaires, int releaseYear, int nbPages, String genre){
        return livreRepository.save(Livre.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).nbPages(nbPages).genre(genre).build());
    }
    public Media saveMedia(String title, String author, String editor, long exemplaires, int releaseYear, String length, MediaType type){
        return mediaRepository.save(Media.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).length(length).type(type).build());
    }
    //TODO FindWithTitle
    //TODO FindWithAuthor
    //TODO FindWithYear
    //TODO FindWithCategory



    //Admin







    //Client
    public Client saveClient(String name, String address){
        return clientRepository.save(Client.builder().clientName(name).clientAddress(address).build());
    }

    //TODO PayDebt

    //TODO Borrow

    //TODO Return

    //TODO GetEmprunts

}
