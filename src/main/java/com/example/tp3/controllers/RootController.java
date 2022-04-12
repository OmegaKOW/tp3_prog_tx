package com.example.tp3.controllers;

import com.example.tp3.forms.ClientForm;
import com.example.tp3.models.users.Client;
import com.example.tp3.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/clients")
    public String getProfs(Model model) {
        model.addAttribute("pageTitle", "Les clients");
        var clients = libraryService.getClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/crudclient")
    public String getProfRequest(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Client");
        var client = new Client();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);;
        if (inputFlashMap != null)
            client = (Client) inputFlashMap.get("client");
        model.addAttribute("client", client);
        return "crudclient";
    }

    @GetMapping("/clientedit/{id}")
    public String getProfRequest(HttpServletRequest request, Model model, @PathVariable(required = false) Long clientId) {
        model.addAttribute("pageTitle", "Client");
        var client = new Client();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null)
            client = (Client) inputFlashMap.get("client");
        model.addAttribute("client", client);
        return "clientedit";
    }

    @PostMapping("/clientcreate")
    public String profPost(@ModelAttribute ClientForm clientForm,
                           BindingResult errors,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        logger.info("client: " + clientForm);
        libraryService.saveClient(clientForm.toClient());
        redirectAttributes.addFlashAttribute("clientForm", clientForm);
        model.addAttribute("pageTitle", "Client");
        model.addAttribute("clientForm", clientForm);
        return "redirect:clientedit/" + clientForm.getClientID();
    }
}
