import Image from "next/image";

var person = {
    name: "Jack",
    lastName: "Sparrow"
};

var hello = <h1 className="text-3xl font-bold">Hello {person.name} {person.lastName}</h1>;

export default function Home() {
  return (
    <div>{hello}</div>
  );
}
