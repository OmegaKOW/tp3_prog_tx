package com.example.tp3.controllers;

import com.example.tp3.forms.ClientForm;
import com.example.tp3.forms.EmpruntForm;
import com.example.tp3.forms.EmpruntGetForm;
import com.example.tp3.models.library.Document;
import com.example.tp3.models.library.Emprunt;
import com.example.tp3.models.library.Livre;
import com.example.tp3.models.library.Media;
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
import java.util.List;
import java.util.Map;

@Controller
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    private LibraryService libraryService;

    public RootController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String getRootRequest(Model model) {
        model.addAttribute("pageTitle", "Mon Demo");
        return "index";
    }

    @GetMapping("/documents")
    public String getIndexRequest(Model model) {
        model.addAttribute("pageTitle", "Mon Demo");
        return "documents";
    }

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
        var client = new Client();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);;
        if (inputFlashMap != null)
            client = (Client) inputFlashMap.get("client");
        model.addAttribute("client", client);
        return "crudclient";
    }


    @GetMapping("/client")
    public String clientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client";
    }

    @PostMapping("/client")
    public String clientSubmit(@ModelAttribute Client client, Model model) {
        libraryService.saveClient(client);
        model.addAttribute("client", client);
        return "resultatClient";
    }



    //--------------------------------------------------------------------------


    @GetMapping("/livres")
    public String getLivres(Model model) {
        model.addAttribute("pageTitle", "Les livres");
        var livres = libraryService.getLivres();
        model.addAttribute("livres", livres);
        return "livres";
    }

    @GetMapping("/livre")
    public String livreForm(Model model) {
        model.addAttribute("livre", new Livre());
        return "livre";
    }

    @PostMapping("/livre")
    public String livreSubmit(@ModelAttribute Livre livre, Model model) {
        libraryService.saveLivre(livre);
        model.addAttribute("livre", livre);
        return "resultatLivre";
    }

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
        final List<Emprunt> emprunts = libraryService.getEmprunts(clientId);
        empruntForm = new EmpruntForm();
        if(!emprunts.isEmpty()){
            for(Emprunt e : emprunts){
                empruntForm = new EmpruntForm(e);
            }
        }
        model.addAttribute("emprunts", emprunts);
        return "showEmprunts";
    }

    //------------------------------------------------------------------------------------


    @GetMapping("/medias")
    public String getMedias(Model model) {
        model.addAttribute("pageTitle", "Les medias");
        var medias = libraryService.getMedias();
        model.addAttribute("medias", medias);
        return "medias";
    }

    @GetMapping("/media")
    public String mediaForm(Model model) {
        model.addAttribute("media", new Media());
        return "media";
    }

    @PostMapping("/media")
    public String mediaSubmit(@ModelAttribute Media media, Model model) {
        libraryService.saveMedia(media);
        model.addAttribute("media", media);
        return "resultatMedia";
    }

    //-------------------------------------------------------

    private long getIdFromString(String id) {
        try {
            return Long.parseLong(id);
        } catch(NumberFormatException e) {}
        return 0;
    }
}
