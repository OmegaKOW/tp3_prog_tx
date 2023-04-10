package com.example.tp3.controllers;

import com.example.tp3.dtos.ClientCreationDTO;
import com.example.tp3.dtos.ClientDTO;
import com.example.tp3.dtos.LivreCreationDTO;
import com.example.tp3.dtos.LivreDTO;
import com.example.tp3.forms.*;
import com.example.tp3.models.library.*;
import com.example.tp3.models.users.Client;
import com.example.tp3.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    private final LibraryService libraryService;

    public RootController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String getRootRequest(Model model) {
        model.addAttribute("pageTitle", "Biblio Javatown");
        return "index";
    }

    @GetMapping("/documents")
    public String getIndexRequest(Model model) {
        model.addAttribute("pageTitle", "Biblio Javatown");
        return "documents";
    }

    //------------------DOCUMENTS--------------------------

    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("pageTitle", "Les clients");
        var clients = libraryService.getClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/crudclient")
    public String getClientRequest(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Client");
        var client = new ClientCreationDTO();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null)
            client = (ClientCreationDTO) inputFlashMap.get("client");
        model.addAttribute("client", client);
        return "crudclient";
    }


    @GetMapping("/client")
    public String clientForm(@ModelAttribute ClientForm clientForm, BindingResult result, Model model) {
        clientForm = new ClientForm(new ClientCreationDTO());
        model.addAttribute("clientForm", clientForm);
        return "client";
    }

    @PostMapping("/client")
    public String clientSubmit(@Valid @ModelAttribute ClientForm client, BindingResult errors, Model model) {
        if(errors.hasErrors()){
            return "client";
        }
        libraryService.saveClient(client.toClient());
        model.addAttribute("client", client);
        return "resultatClient";
    }



    //---------------------------LIVRE-----------------------------------------------


    @GetMapping("/livres")
    public String getLivres(Model model) {
        model.addAttribute("pageTitle", "Les livres");
        var livres = libraryService.getLivres();
        model.addAttribute("livres", livres);
        return "livres";
    }

    @GetMapping("/livre")
    public String livreForm(@ModelAttribute LivreForm livreForm,BindingResult result, Model model) {
        livreForm = new LivreForm(new LivreCreationDTO());
        model.addAttribute("livreForm", livreForm);
        return "livre";
    }

    @PostMapping("/livre")
    public String livreSubmit(@Valid @ModelAttribute LivreForm livreForm,BindingResult errors, Model model) {
        if(errors.hasErrors()){
            return "livre";
        }
        libraryService.saveLivre(livreForm.toLivre());
        model.addAttribute("livre", livreForm);
        return "resultatLivre";
    }




    //---------------------------MÉDIA---------------------------------------------------------


    @GetMapping("/medias")
    public String getMedias(Model model) {
        model.addAttribute("pageTitle", "Les medias");
        var medias = libraryService.getMedias();
        model.addAttribute("medias", medias);
        return "medias";
    }

    @GetMapping("/media")
    public String mediaForm(@ModelAttribute MediaForm mediaForm,BindingResult result, Model model) {
        mediaForm = new MediaForm(new Media());
        model.addAttribute("mediaForm", mediaForm);
        return "media";
    }

    @PostMapping("/media")
    public String mediaSubmit(@Valid @ModelAttribute MediaForm media,BindingResult errors, Model model) {
        if(errors.hasErrors()){
            return "media";
        }
        libraryService.saveMedia(media.toMedia());
        model.addAttribute("media", media);
        return "resultatMedia";
    }


    //----------------EMPRUNTS-----------------------

    @GetMapping("/getEmpruntsWithClientId")
    public String getEmpruntsWithClientId(@ModelAttribute EmpruntGetForm empruntGetForm, Model model){


        model.addAttribute("empruntGetForm",empruntGetForm);
        model.addAttribute("id", empruntGetForm.getId());

        return "getEmprunts";
    }

    @PostMapping("/getEmpruntsWithClientId")
    public RedirectView setEmpruntsWithClientId(@ModelAttribute EmpruntGetForm empruntGetForm, RedirectAttributes redirectAttributes){


        redirectAttributes.addFlashAttribute("empruntGetForm",empruntGetForm);
        List<Emprunt> emprunts = libraryService.getEmprunts(getIdFromString(empruntGetForm.getId()));
        redirectAttributes.addAttribute("id", empruntGetForm.getId());
        redirectAttributes.addAttribute("emprunts", emprunts);


        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/getEmprunts/{id}");
        return redirectView;
    }

    @GetMapping("/getEmprunts/{id}")
    public String getEmpruntsWithClientId(Model model, @ModelAttribute EmpruntForm empruntForm, @PathVariable("id") String id){
        logger.info("Id: " + id);
        long clientId = getIdFromString(id);
        List<Emprunt> emprunts = libraryService.getEmprunts(clientId);
        model.addAttribute("emprunts", emprunts);
        return "showEmprunts";
    }


    //-------------------------RETOURS--------------------------------

    @GetMapping("/returnDocument")
    public String returnDocument(Model model, RetourForm retourForm){
        model.addAttribute("empruntGetForm",retourForm);
        model.addAttribute("title", retourForm.getTitle());

        return "retourDocument";
    }

    @PostMapping("/returnDocument")
    public RedirectView returnDocument(RedirectAttributes redirectAttributes, @ModelAttribute RetourForm retourForm){


        try {
            long clientId = getIdFromString(retourForm.getId());
            long documentId = libraryService.findDocumentWithTitleTop(retourForm.getTitle()).getDocumentID();
            libraryService.returnDocument(documentId,clientId);

            redirectAttributes.addAttribute("id", clientId);
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/getEmprunts/{id}");

            return redirectView;
        }catch (Exception e){
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/returnDocument");
            return redirectView;
        }




    }


    //------------------TROUVER UN DOCUMENT------------------------
    @GetMapping("/getDocumentWithTitle")
    public String getDocumentForEmprunt(@ModelAttribute DocumentEmpruntForm documentForm,BindingResult result, Model model){
        model.addAttribute("documentForm", documentForm);

        return "getDocumentWithTitle";
    }

    @PostMapping("/getDocumentWithTitle")
    public RedirectView getDocumentForEmprunt(@Valid @ModelAttribute DocumentEmpruntForm documentForm,BindingResult errors, RedirectAttributes redirectAttributes) throws Exception {
        //Logging
        logger.info("Title " + documentForm.getTitle());
        logger.info("Client " + documentForm.getClientId());
        RedirectView redirectView = new RedirectView();
        if(errors.hasErrors()){
            redirectAttributes.addFlashAttribute("documentEmpruntForm", documentForm);
            redirectView.setContextRelative(true);
            redirectView.setUrl("/getDocumentWithTitle");
            return redirectView;
        }

        redirectAttributes.addFlashAttribute("documentForm", documentForm);

        try{
            libraryService.findClientWithId(getIdFromString(documentForm.getClientId())).get();
        }catch (Exception e){
            redirectView.setContextRelative(true);
            redirectView.setUrl("/getDocumentWithTitle");
            return redirectView;
        }

        Client client = libraryService.findClientWithId(getIdFromString(documentForm.getClientId())).get();
        try{
            Document document = libraryService.findDocumentWithTitleTop(documentForm.getTitle());
            redirectAttributes.addAttribute("doc", document.getDocumentID());
            redirectAttributes.addAttribute("cli", client.getClientID());
            redirectAttributes.addAttribute("document", document);
            redirectAttributes.addAttribute("client", client);

            libraryService.borrowDocument(client.getClientID(), document.getDocumentID());

            redirectView.setContextRelative(true);
            redirectView.setUrl("/getEmprunts/{cli}");
            return redirectView;


        }catch (Exception e){
            redirectAttributes.addFlashAttribute("documentEmpruntForm", documentForm);
            redirectView.setContextRelative(true);
            redirectView.setUrl("/getDocumentWithTitle");
            return redirectView;
        }




    }

    //---------------------DETTES---------------------------------------

    @GetMapping("/getDettesWithClientId")
    public String getDettesWithClientId(@Valid @ModelAttribute EmpruntGetForm empruntGetForm, Model model){


        model.addAttribute("empruntGetForm",empruntGetForm);
        model.addAttribute("id", empruntGetForm.getId());

        return "getDettes";
    }

    @PostMapping("/getDettesWithClientId")
    public RedirectView setDettesvWithClientId(@ModelAttribute EmpruntGetForm empruntGetForm, RedirectAttributes redirectAttributes){


        redirectAttributes.addFlashAttribute("empruntGetForm",empruntGetForm);
        Set<Dette> dettes = libraryService.getDettesWithClientId(getIdFromString(empruntGetForm.getId()));
        redirectAttributes.addAttribute("id", empruntGetForm.getId());
        redirectAttributes.addAttribute("dettes", dettes);


        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/getDettes/{id}");
        return redirectView;
    }

    @GetMapping("/getDettes/{id}")
    public String getDettesWithClientId(Model model, @ModelAttribute EmpruntForm empruntForm, @PathVariable("id") String id){
        logger.info("Id: " + id);
        long clientId = getIdFromString(id);
        final Set<Dette> dettes = libraryService.getDettesWithClientId(clientId);
        model.addAttribute("dettes", dettes);
        return "showDettes";
    }


    @GetMapping("/payDettes")
    public String getPayDebts(@ModelAttribute EmpruntGetForm empruntGetForm,BindingResult result, Model model){
        model.addAttribute("empruntGetForm", empruntGetForm);

        return "payDettes";
    }
    @PostMapping("/payDettes")
    public RedirectView getPayDebts(@Valid @ModelAttribute EmpruntGetForm empruntGetForm,BindingResult errors, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("empruntGetForm", empruntGetForm);
        try{
            libraryService.findClientWithId(getIdFromString(empruntGetForm.getId())).get();
        }catch (Exception e){

            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/payDettes");
            return redirectView;
        }

        Client client = libraryService.findClientWithId(getIdFromString(empruntGetForm.getId())).get();
        if(client == null){
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/payDettes");
            return redirectView;
        }
        redirectAttributes.addAttribute("cli", client.getClientID());

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        if(errors.hasErrors()){
            redirectView.setUrl("/payDettes");
        }
        redirectView.setUrl("/payDettes/{cli}");
        return redirectView;
    }

    @GetMapping("/payDettes/{id}")
    public RedirectView payDettesWithClientId(Model model, @Valid @ModelAttribute EmpruntForm empruntForm, @PathVariable("id") String id, RedirectAttributes redirectAttributes){
        logger.info("Id: " + id);
        long clientId = getIdFromString(id);
        libraryService.payDebts(clientId);

        redirectAttributes.addAttribute("cli", clientId);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/getDettes/{cli}");
        return redirectView;

    }


    //-------------------------------------------------------

    private long getIdFromString(String id) {
        try {
            return Long.parseLong(id);
        } catch(NumberFormatException e) {}
        return 0;
    }
}
