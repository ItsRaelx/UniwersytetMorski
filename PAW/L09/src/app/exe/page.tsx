import Image from "next/image";

var people = [
    {
        name: "Jack",
        lastName: "Sparrow"
    },
    {
        name: "Gerald",
        lastName: "Rivia"
    },
    {
        name: "Bruce",
        lastName: "Wayne"
    },
    {
        name: "Peter",
        lastName: "Parker"
    }
];

function SayHello(person: any) {
    return "Hello " + person.name + " " + person.lastName;
}

var greetings01 = SayHello(people[0]);
var greetings02 = SayHello(people[1]);
var greetings03 = SayHello(people[2]);
var greetings04 = SayHello(people[3]);

var hello = <h1 className="text-3xl font-bold">Hello World</h1>;

export default function Home() {
  return (
    <div className="greetings">
        <h1 className="text-4xl font-bold">{greetings01}</h1>
        <h2 className="text-3xl font-bold">{greetings02}</h2>
        <h3 className="text-2xl font-bold">{greetings03}</h3>
        <h4 className="text-1xl font-bold">{greetings04}</h4>
    </div>
  );
}
