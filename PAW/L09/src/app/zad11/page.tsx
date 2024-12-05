"use client"
import Image from "next/image";
import { useState } from "react";

interface ButtonProps {
    owner: string;
}

function Button({ owner }: ButtonProps) {
    const [clickedCounter, setClickedCounter] = useState<number>(0);
    
    const handleClick = () => {
        setClickedCounter(prevCount => prevCount + 1);
    };

    return (
        <button 
            onClick={handleClick}
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
            {owner}'s button, clicked {clickedCounter} times!
        </button>
    );
}

interface PersonProps {
    name: string;
    lastName: string;
    age: number;
}

function Person({ name, lastName, age }: PersonProps) {
    return (
        <div className="person">
            <h2 className="text-2xl font-bold">
                Name:{name}, Last Name:{lastName},
                Age:{age} {age >= 28 ? "- Old." : "- Young."}
                <br />
                <Button owner={name} />
            </h2>
            <br />
        </div>
    );
}

const people: PersonProps[] = [
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
];

const listPeople = people.map((person, index) => (
    <Person 
        key={index}
        name={person.name} 
        lastName={person.lastName} 
        age={person.age} 
    />
));

export default function Home() {
    return (
        <div className="greetings">
            {listPeople}
        </div>
    );
}