'use client'

import { useEffect, useRef, useState } from 'react'
import dynamic from 'next/dynamic'

// Dynamically import Three.js
// @ts-ignore
const THREE = dynamic(() => import('three'), { ssr: false })

function getRandomValue(min: number, max: number) {
    return Math.random() * (max - min + 1) + min;
}

function createCubeAttributes() {
    const minValue = -2;
    const maxValue = 2;
    const randomColor = Math.floor(Math.random() * 16777215).toString(16);
    return {
        width: getRandomValue(minValue, maxValue)/2,
        height: getRandomValue(minValue, maxValue)/2,
        depth: getRandomValue(minValue, maxValue)/2,
        color: randomColor,
        position: {
            x: getRandomValue(minValue, maxValue),
            y: getRandomValue(minValue, maxValue),
            z: getRandomValue(minValue, maxValue)
        }
    };
}

export default function ThreeScene() {
    const containerRef = useRef<HTMLDivElement>(null)
    const [threeLoaded, setThreeLoaded] = useState(false)

    useEffect(() => {
        if (typeof window === 'undefined' || !containerRef.current) return

        // Import Three.js dynamically
        import('three').then((THREE) => {
            setThreeLoaded(true)

            const scene = new THREE.Scene()
            const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
            const renderer = new THREE.WebGLRenderer({ antialias: true })

            renderer.setSize(window.innerWidth, window.innerHeight)
            // @ts-ignore
            containerRef.current.appendChild(renderer.domElement)

            camera.position.z = 5

            const numberOfCubes = 50;
            // @ts-ignore
            const cubes: THREE.Mesh[] = [];

            function createCube(numberOfCubes: number) {
                for (let i = 0; i < numberOfCubes; i++) {
                    const cubeAttributes = createCubeAttributes();
                    const geometry = new THREE.BoxGeometry(cubeAttributes.width, cubeAttributes.height, cubeAttributes.depth);
                    const material = new THREE.MeshBasicMaterial({ color: parseInt(cubeAttributes.color, 16), wireframe: false });
                    const cube = new THREE.Mesh(geometry, material);
                    cube.position.set(cubeAttributes.position.x, cubeAttributes.position.y, cubeAttributes.position.z);
                    cubes.push(cube);
                    scene.add(cube);
                }
            }

            createCube(numberOfCubes);

            function animateScene() {
                for (let i = 0; i < numberOfCubes; i++) {
                    cubes[i].rotation.x += 0.01;
                    cubes[i].rotation.y += 0.01;
                    cubes[i].rotation.z += 0.01;
                }

                requestAnimationFrame(animateScene);
                renderer.render(scene, camera);
            }

            animateScene();

            const handleResize = () => {
                const width = window.innerWidth
                const height = window.innerHeight
                renderer.setSize(width, height)
                camera.aspect = width / height
                camera.updateProjectionMatrix()
            }

            window.addEventListener('resize', handleResize)

            return () => {
                window.removeEventListener('resize', handleResize)
                containerRef.current?.removeChild(renderer.domElement)
            }
        })
    }, [])

    return <div ref={containerRef} style={{ width: '100%', height: '100vh' }} />
}

