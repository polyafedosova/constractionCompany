package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("clients")
    public String getClient(Model model) {
        List<Client> listBuildings = new LinkedList<>(clientService.getAll());
        model.addAttribute("clients", listBuildings);
        return "clientsList";
    }

    @GetMapping("clients/{client}/editClient")
    public String getEditClientPage(Model model, @PathVariable(required = false) Client client) {
        model.addAttribute("client", client);
        return "clientEdit";
    }

    @PostMapping("clients/{client}/editClient")
    public String editClient(Model model, @ModelAttribute Client client) {
        model.addAttribute("client", client);
        if (!clientService.updateClient(client)) {
            model.addAttribute("message", "Введены некорректные данные");
        } else {
            model.addAttribute("message", "Данные успешно изменены");
        } return "clientEdit";
    }

    @PostMapping("clients/addClient")
    public String addClient(Model model, @ModelAttribute Client client,
                              RedirectAttributes redirectAttributes) {
        List<Client> listClients = new LinkedList<>(clientService.getAll());
        model.addAttribute("clients", listClients);
        if (!clientService.isDataCorrectly(client)) {
            redirectAttributes.addFlashAttribute("message", "Введены некорректные данные");
            redirectAttributes.addFlashAttribute("clientToAdd", client);
            return "redirect:/clients";
        }
        if (!clientService.saveClient(client)) {
            redirectAttributes.addFlashAttribute("message", "Данный клиент уже существует");
            redirectAttributes.addFlashAttribute("clientToAdd", client);
            return "redirect:/clients";
        }
        redirectAttributes.addFlashAttribute("message", "Клиент успешно добавлен");
        return "redirect:/clients";
    }

    @GetMapping("clients/{client}/deleteClient")
    public String deleteClient(@PathVariable Client client, RedirectAttributes redirectAttributes) {
        if (!clientService.deleteClient(client)) {
            redirectAttributes.addFlashAttribute("message", "Неизвестная ошибка");
        } else redirectAttributes.addFlashAttribute("message", "Строение успешно удалено");
        return "redirect:/clients";
    }
}
