package Hero.service;

import Hero.entity.Hero;
import Hero.repository.HeroRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    public List<Hero> getHeroes(){
        return repository.findAll();
    }

    public Hero getHeroById(String id){
        // Note: boolean isPresent()：如果值存在，回傳 true；不存在則回傳 false。
        Optional<Hero> result = repository.findById(id);
        return result.isPresent() ? result.get() : new Hero();
    }

    public List<Hero> getHeroBySearchName(String name){
        return repository.findByNameLikeOrderByIdAsc(name);
    }

    public Hero updateHeroName(Hero hero){
        return repository.save(hero);
    }

    public void DeleteHero(String id){
        repository.deleteById(id);
        // return status or erroe message could be better
        return;
    }

    public Hero addHero(Hero hero){
        return repository.insert(hero);
    }

}