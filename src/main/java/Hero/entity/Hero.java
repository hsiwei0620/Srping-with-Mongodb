package Hero.entity;

import org.springframework.data.annotation.Id;

public class Hero {

    @Id
    public String id;
    public String name;


    public Hero() {
    }

    public Hero(String name){
        this.name = name;
    }

    public Hero(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Hero[id=%s, name='%s']",
                id, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}