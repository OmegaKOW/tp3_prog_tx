package com.example.tp3.service;

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


    //Admin







    //Client
}
