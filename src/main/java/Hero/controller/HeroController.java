package Hero.controller;

import Hero.constants.GeneralConstants;
import Hero.entity.Hero;
import Hero.service.HeroService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(GeneralConstants.API_HEROES)
public class HeroController {

    @Autowired
    private HeroService heroService;

    @ApiOperation(value = "Find All Hero")
    @GetMapping
    public List<Hero> GetHeroes(){
        return heroService.getHeroes();
    }

    @ApiOperation(value = "Find Hero By ID")
    @GetMapping(value = "/{id}")
    public Hero GetHero(final @PathVariable("id") String id){
        return heroService.getHeroById(id);
    }

    @ApiOperation(value = "Search Hero By Name(blurry search)")
    @GetMapping(value = "/name/{name}")
    public List<Hero> GetSearchHeroes(final @PathVariable("name") String name){
        return heroService.getHeroBySearchName(name);
    }

    @ApiOperation(value = "Update Hero Name")
    @PutMapping
    public Hero UpdateHero(@RequestBody Hero hero){
        return heroService.updateHeroName(hero);
    }

    @ApiOperation(value = "Delete Hero By ID")
    @DeleteMapping(path ={"/{id}"})
    public void DeleteHero(@PathVariable("id") String id) {
        heroService.DeleteHero(id);
    }

    @ApiOperation(value = "Add Hero ")
    @PostMapping
    public Hero AddHero(@RequestBody Hero hero){
        return heroService.addHero(hero);
    }
}
