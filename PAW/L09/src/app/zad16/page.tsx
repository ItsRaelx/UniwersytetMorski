"use client"
import axios from "axios";
import Image from "next/image";
import React, { useEffect, useState } from "react";

interface DogAppProps {
    numberOfDogs: number;
}

function DogApp({ numberOfDogs }: DogAppProps) {
    const [dogs, setDogs] = useState<string[]>([]);
    const [defaultImage, setDefaultImage] = useState<string>("");
    const mainlink: string = `https://dog.ceo/api/breeds/image/random/${numberOfDogs}`;
    const defaultLink: string = "https://images.dog.ceo/breeds/image/random";

    const replaceImage = (error: any) => {
        if (error.target) {
            error.target.src = defaultImage;
        }
    };

    useEffect(() => {
        axios.get(mainlink)
            .then((response) => {
                setDogs(response.data.message);
            })
            .catch((error) => {
                console.error(error);
                setDefaultImage(defaultLink);
            });
    }, [mainlink]);

    return (
        <div className="grid grid-cols-3 gap-4 p-4">
            {dogs.map((dogUrl, index) => (
                <div key={index} className="relative w-full h-[200px]">
                    <Image 
                        src={dogUrl}
                        alt={`Dog ${index + 1}`}
                        fill
                        className="object-cover rounded-lg"
                        onError={replaceImage}
                    />
                </div>
            ))}
        </div>
    );
}

export default function Home() {
    return (
        <div>
            <DogApp numberOfDogs={25} />
        </div>
    );
}