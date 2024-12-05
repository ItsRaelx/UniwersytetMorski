import Image from "next/image";

function Person(props: any) {
    return <div className="person">
        <h2 className="text-2xl font-bold">
            Name:{props.name}, Last Name:{props.lastName},
            Age:{props.age} {props.age >= 28 ? "Old" : "Young"}
        </h2>
    </div>
}

var prople = [
    {
        name: "Jack",
        lastName: "Sparrow",
        age: 27
    },
    {
        name: "Gerald",
        lastName: "Rivia",
        age: 106
    },
    {
        name: "Bruce",
        lastName: "Wayne",
        age: 30
    },
    {
        name: "Peter",
        lastName: "Parker",
        age: 25
    }
]

var listPeople = prople.map((person: any, index: number) => {
    return <Person 
        key={index}
        name={person.name} 
        lastName={person.lastName} 
        age={person.age} 
    />
})

export default function Home() {
  return (
    <div className="greetings">
        {listPeople}
    </div>
  );
}
