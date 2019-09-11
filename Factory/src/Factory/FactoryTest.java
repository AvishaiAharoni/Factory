package Factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertSame;

import java.util.function.Function;


public class FactoryTest {

	class AnimalCreation implements Function<Integer, Animal>{

		AnimalCreation(){};
		
		@Override
		public Animal apply(Integer legs) {
			
			return new Animal(legs);
		}
	}
	
	class CatCreation implements Function<Integer, Cat>{

		CatCreation(){};
		
		@Override
		public Cat apply(Integer legs) {
			
			return new Cat(legs);
		}

	}
	
	class Animal{
		int legs;
		
		Animal(int legs){
			this.legs = legs;
		}
		
		void cure() {
			this.legs = 4;
		}
		
		int countLegs() {
			return this.legs;
		}		
	}
	
	class Cat extends Animal{
		int tail;
		
		Cat(int legs){
			super(legs);
			this.tail = 0;
		}
		
		@Override
		void cure() {
			this.legs = 8;
			this.tail = 1;
		}
		
		int countTails() {
			return this.tail;
		}	
		
	}
	
	@Test
	void testAdd() {
		Factory<String, Animal, Integer> fc = new Factory<>();
		
		AnimalCreation n = new AnimalCreation();
		
		assertSame(fc.add("Animal", n), null);
		
		
		assertSame(fc.add("Animal", new AnimalCreation()), n);
	}
	
	@Test
	void testCreate() {
		Factory<String, Animal, Integer> fc = new Factory<>();
		
		fc.add("Animal", new AnimalCreation());
		
		Integer legs = 7;
		
		Animal a = fc.create("Animal", legs);
		
		assertSame(a.countLegs(), legs);
	}
	
	@Test
	void testCreateTypes() {
		Factory<String, Animal, Integer> fc = new Factory<>();
		
		fc.add("Animal", new AnimalCreation());
		fc.add("Cat", new CatCreation());
		
		Integer animalLegs = 7;
		Integer catLegs = 6;
		Integer cueredCatLegs = 8;
		Integer cueredAnimalLegs = 4;
		
		Animal a = fc.create("Animal", animalLegs);
		Animal cat = fc.create("Cat", catLegs);
		
		assertSame(cat.countLegs(), catLegs);
		cat.cure();
		assertSame(cat.countLegs(), cueredCatLegs);
		
		assertSame(a.countLegs(), animalLegs);
		a.cure();
		assertSame(a.countLegs(), cueredAnimalLegs);
	}

}