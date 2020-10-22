package com.guestbook.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.guestbook.exception.RecordNotFoundException;
import com.guestbook.model.GuestEntity;

@Controller
@RequestMapping("/")
public class GuestMvcController {
	
    private final static Logger logger = LoggerFactory.getLogger(GuestMvcController.class);
    
	@Value("${url.api}")
    private String URL;
	
	private RestTemplate getTemplate() {
        RestTemplate rest = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();

        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);

        converter.setSupportedMediaTypes(mediaTypes);
        rest.getMessageConverters().add(converter);

        return rest;
    }
	
	@RequestMapping
	public String getAllGuest(Model model) {
		List<GuestEntity> list = new ArrayList<>();
		try {
			HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            ResponseEntity<List<GuestEntity>> res = getTemplate().exchange(URL + "/api/guest",
                    HttpMethod.GET,entity,new ParameterizedTypeReference<List<GuestEntity>>() {});
            list = res.getBody(); 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		model.addAttribute("guest_all", list);
		return "list-guest";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editGuestById(Model model, @PathVariable(value = "id", required = false) Long id) throws RecordNotFoundException {
        if (id != null) {
        	GuestEntity entity = new GuestEntity();
        	try {
        		HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> ent = new HttpEntity<String>("", headers);
                ResponseEntity<GuestEntity> res = getTemplate().exchange(URL + "/api/guest/" + id,
                        HttpMethod.GET,ent,new ParameterizedTypeReference<GuestEntity>() {});
                entity = res.getBody(); 
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
            
            model.addAttribute("guest", entity);
            return "edit-guest";
        } else {
            model.addAttribute("guest", new GuestEntity());
            return "add-guest";
        }
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteGuestById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
    	try {
    		HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> ent = new HttpEntity<String>("", headers);
            ResponseEntity<GuestEntity> res = getTemplate().exchange(URL + "/api/guest/" + id,
                    HttpMethod.DELETE,ent,new ParameterizedTypeReference<GuestEntity>() {});
            logger.info(res.getBody().toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return "redirect:/";
	}

	@RequestMapping(path = "/createGuest", method = RequestMethod.POST)
	public String createOrUpdateGuest(GuestEntity guest) {
        try {
        	ResponseEntity<GuestEntity> res = getTemplate().postForEntity(URL + "/api/guest/save",
        			guest,GuestEntity.class);
        	logger.info(res.getBody().toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return "redirect:/";
	}
}
