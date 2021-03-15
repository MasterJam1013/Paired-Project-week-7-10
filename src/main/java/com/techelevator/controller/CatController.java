package com.techelevator.controller;

import com.techelevator.model.*;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CatController {

    private CatCardDAO catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDAO catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }
//Provides a new, randomly created Cat Card containing information from the cat fact and picture services.
    @RequestMapping(path= "/random", method = RequestMethod.GET)
    public CatCard createNewRandomCard(){

        CatCard card = new CatCard();

        CatPic pic= catPicService.getPic();
        CatFact fact= catFactService.getFact();

        card.setCatFact(fact.getText());
        card.setImgUrl(pic.getFile());

        return card;
    }
//Saves a card to the user's collection.
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="", method = RequestMethod.POST)
    public void saveCardTOCollection( @Valid @RequestBody CatCard catCard) throws CatCardNotFoundException{

        catCardDao.save(catCard);
    }

    //Updates a card in the user's collection.
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public  void update(@Valid @RequestBody CatCard catCard, @PathVariable long catCardId) throws CatCardNotFoundException{
        catCardDao.update(catCardId, catCard);

    }
    //Removes a card from the user's collection.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) throws CatCardNotFoundException{
        catCardDao.delete(id);
    }
    // Provides a Cat Card with the given ID.
    @RequestMapping(path= "/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable int id)throws CatCardNotFoundException{
        return catCardDao.get(id);
    }
    // Provides a list of all Cat Cards in the user's collection.
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CatCard> list() {
        return catCardDao.list();
    }

}




















































