package Hero.repository;

import Hero.entity.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HeroRepository extends MongoRepository<Hero, String> {
    // 模糊查詢
    public List<Hero> findByNameLikeOrderByIdAsc(String Name);

//    public List<Hero> findByName(String Name);
//    public List<Hero> findByLastName(String lastName);
//    這樣寫是不行的, 因為Repository會去底層找 Mongod "HeroId"的欄位,但是我根本沒有這個欄位..
//    public Hero findByHeroId(String Id);

}