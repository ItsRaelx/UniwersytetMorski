import Image from "next/image";

function Person(props: any) {
    return <div className="person">
        <h2 className="text-2xl font-bold">
            Name:{props.name},
            Last Name:{props.lastName},
            Age:{props.age}
        </h2>
    </div>
}

export default function Home() {
  return (
    <div className="greetings">
        <Person name="Jack" lastName="Sparrow" age={25} />
        <Person name="Gerald" lastName="Rivia" age={30} />
        <Person name="Peter" lastName="Parker" age={35} />
    </div>
  );
}
