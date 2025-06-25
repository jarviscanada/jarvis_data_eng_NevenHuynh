class Person {
  // Constructor runs when a new object is created
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  // Method to greet
  greet() {
    console.log(`Hi, my name is ${this.name} and I'm ${this.age} years old.`);
  }

  // Method to increment age
  haveBirthday() {
    this.age += 1;
    console.log(`It's my birthday! I'm now ${this.age} years old.`);
  }
}

// Create an object (instance) of the class
const person1 = new Person("Eric", 69);

// Call methods on the object
person1.greet();
person1.haveBirthday();