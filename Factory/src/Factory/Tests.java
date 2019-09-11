package Factory;

import java.util.function.Function;

class Animal {
	protected int ID;

	public Animal(){
		System.out.println("Animal Ctor");
	}

	public Animal(int id){
		System.out.println("Animal Ctor int");
		this.ID = id;
	}

	public void sayHello(){
		System.out.println("Animal Hello!");
		System.out.println("My ID is "+ ID);
	}

	@Override
	public String toString(){
		return "Animal with ID: " + ID; 
	}
}

class Dog extends Animal {

	public Dog(){
		System.out.println("Dog Ctor");
	}

	public Dog(int id){
		System.out.println("Dog Ctor int");
		this.ID = id;
	}

	public void sayHello(){
		System.out.println("Dog Hello!");
		System.out.println("My ID is "+ ID);
	}

	@Override
	public String toString(){
		return "Dog with ID: " + ID; 
	}
}

class Cat extends Animal{

	public Cat(){
		System.out.println("Cat Ctor");
		this.ID = 2;
	}

	public void sayHello(){
		System.out.println("Cat Hello!");
		System.out.println("My ID is "+ ID);
	}

	public Cat(int id){
		this.ID = id;
		System.out.println("Cat Ctor int");
	}

	@Override
	public String toString(){
		return "Cat with ID: " + ID; 
	}
}

class LegendaryAnimal extends Cat {

	public LegendaryAnimal(int id){
		System.out.println("Legendary Ctor");
		this.ID = id;
	}

	public void sayHello(){
		System.out.println("Legendary Hello!");
	}

	@Override
	public String toString(){
		return "LegendaryAnimal with ID: " + ID; 
	}
}

class AnimalCreation {
	static Animal CreateAnimal(int arg) {
		return (new Animal(arg));
	}
}


public class Tests {
	public static void main(String[] args) {
		Factory<String, Animal, Integer> fc = new Factory<>();

		fc.add("animal", new Function<Integer, Animal>() {					 //anonymous class

			@Override
			public Animal apply(Integer t) {
				return new Animal(t);
			}
		});

		fc.add("cat", (Integer t)-> new Cat(t)); 							//lambda

		fc.add("dog", Dog::new);											//functional reference

		Animal myAnimal = fc.create("animal", 3);
		myAnimal.sayHello();

		Animal myCat = fc.create("cat", 45);
		myCat.sayHello();
	}
}