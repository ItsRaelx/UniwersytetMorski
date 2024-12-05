"use client"
import axios from "axios";
import React, { useEffect, useState } from "react";

interface Character {
    name: string;
    height: string;
    mass: string;
    birth_year: string;
    gender: string;
}

function StarWarsApp() {
    const [characters, setCharacters] = useState<Character[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    useEffect(() => {
        setLoading(true);
        axios.get('https://swapi.dev/api/people')
            .then((response) => {
                setCharacters(response.data.results);
                setLoading(false);
            })
            .catch((error) => {
                setError("Failed to fetch Star Wars characters");
                setLoading(false);
                console.error(error);
            });
    }, []);

    if (loading) return <div className="text-center text-2xl p-4">Loading...</div>;
    if (error) return <div className="text-center text-red-500 p-4">{error}</div>;

    return (
        <div className="p-8">
            <h1 className="text-4xl font-bold text-center mb-8">Star Wars Characters</h1>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {characters.map((character, index) => (
                    <div 
                        key={index}
                        className="bg-gray-800 p-6 rounded-lg shadow-lg hover:transform hover:scale-105 transition duration-300"
                    >
                        <h2 className="text-2xl font-bold text-yellow-400 mb-4">{character.name}</h2>
                        <ul className="space-y-2 text-gray-300">
                            <li><span className="font-semibold">Height:</span> {character.height}cm</li>
                            <li><span className="font-semibold">Mass:</span> {character.mass}kg</li>
                            <li><span className="font-semibold">Birth Year:</span> {character.birth_year}</li>
                            <li><span className="font-semibold">Gender:</span> {character.gender}</li>
                        </ul>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default function Home() {
    return (
        <div className="min-h-screen bg-gray-900 text-white">
            <StarWarsApp />
        </div>
    );
}