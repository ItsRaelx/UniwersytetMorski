'use client'

import { useRef, useState, Suspense } from 'react'
import { Canvas, useFrame, useLoader } from '@react-three/fiber'
import { OrbitControls, Plane, Box } from '@react-three/drei'
import * as THREE from 'three'

function getRandomValue(min: number, max: number) {
    return Math.random() * (max - min + 1) + min;
}

function createCubeAttributes() {
    const minValue = -2;
    const maxValue = 2;
    const randomColor = Math.random() * 0xffffff;
    return {
        width: getRandomValue(minValue, maxValue)/2,
        height: getRandomValue(minValue, maxValue)/2,
        depth: getRandomValue(minValue, maxValue)/2,
        color: randomColor,
        position: [
            getRandomValue(minValue, maxValue),
            getRandomValue(minValue, maxValue),
            getRandomValue(minValue, maxValue)
        ] as [number, number, number]
    };
}

function RotatingCube({ width, height, depth, color, position }: ReturnType<typeof createCubeAttributes>) {
    const mesh = useRef<THREE.Mesh>(null!)
    const texture = useLoader(THREE.TextureLoader, '/wood.png')

    useFrame((state, delta) => {
        mesh.current.rotation.x += 0.01
        mesh.current.rotation.y += 0.01
        mesh.current.rotation.z += 0.01
    })

    return (
        <Box args={[width, height, depth]} position={position} ref={mesh}>
            <meshPhongMaterial map={texture} color={color} />
        </Box>
    )
}

function CheckerPlane() {
    const texture = useLoader(THREE.TextureLoader, '/checker.png')
    texture.wrapS = texture.wrapT = THREE.RepeatWrapping
    texture.repeat.set(40, 40)
    texture.magFilter = THREE.NearestFilter
    texture.colorSpace = THREE.SRGBColorSpace

    return (
        <Plane args={[40, 40]} rotation={[-Math.PI / 2, 0, 0]} position={[0, -5, 0]}>
            <meshBasicMaterial map={texture} side={THREE.DoubleSide} />
        </Plane>
    )
}

function Scene() {
    const [cubes] = useState(() =>
        Array.from({ length: 50 }, () => createCubeAttributes())
    )

    return (
        <>
            <ambientLight intensity={1} color={0xFF000AA} />
            <directionalLight position={[-1, 2, 4]} intensity={1} />
            <Suspense fallback={null}>
                <CheckerPlane />
                {cubes.map((props, index) => (
                    <RotatingCube key={index} {...props} />
                ))}
            </Suspense>
            <OrbitControls
                enableDamping
                dampingFactor={0.05}
                screenSpacePanning={false}
                minDistance={1}
                maxDistance={50}
                maxPolarAngle={Math.PI / 2}
            />
        </>
    )
}

export function ThreeScene() {
    return (
        <div style={{ width: '100%', height: '100vh' }}>
            <Canvas
                camera={{ position: [0, 0, 5], fov: 75, near: 0.1, far: 1000 }}
            >
                <Scene />
            </Canvas>
        </div>
    )
}

export default function ThreeSceneWrapper() {
    return (
        <Suspense fallback={<div>Loading 3D scene...</div>}>
            <ThreeSceneDynamic />
        </Suspense>
    )
}
