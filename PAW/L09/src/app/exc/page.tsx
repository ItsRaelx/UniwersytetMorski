import Image from "next/image";

var name: string = "Jack";
var lastName: string = "Sparrow";
var hello = <h1 className="text-3xl font-bold">Hello {name} {lastName}</h1>;

export default function Home() {
  return (
    <div>{hello}</div>
  );
}
