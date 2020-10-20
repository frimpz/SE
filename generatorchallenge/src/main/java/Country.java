import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

    //@Entity
    //@Table(name="Country")
    public class Country {

        private String key;
        private String name;
        private Integer population ;
        private String continent ;
        private String citizens;

        //@Column(name = "name", unique=true)
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Country() {

        }

        //@Column(name = "name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //@Column(name = "population")
        public Integer getPopulation() {
            return population;
        }

        public void setPopulation(Integer population) {
            this.population = population;
        }

        //@Column(name = "continent")
        public String getContinent() {
            return continent;
        }

        public void setContinent(String continent) {
            this.continent = continent;
        }

        //@Column(name = "citizens", columnDefinition="Name of Citizens")
        public String getCitizens() {
            return citizens;
        }

        public void setCitizens(String citizens) {
            this.citizens = citizens;
        }
    }
