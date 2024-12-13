'use client'

import { useEffect, useRef, useState } from 'react'
import dynamic from 'next/dynamic'

// Dynamically import Three.js
// @ts-ignore
const THREE = dynamic(() => import('three'), { ssr: false })
// @ts-ignore
const OrbitControlsModule = dynamic(
    // @ts-ignore
    () => import('three/examples/jsm/controls/OrbitControls'),
    { ssr: false }
)

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

        let animationFrameId: number;

        // Import Three.js and OrbitControls dynamically
        // @ts-ignore
        Promise.all([
            import('three'),
            // @ts-ignore
            import('three/examples/jsm/controls/OrbitControls')
        ]).then(([THREE, { OrbitControls }]) => {
            setThreeLoaded(true)

            const scene = new THREE.Scene()
            const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
            const renderer = new THREE.WebGLRenderer({ antialias: true })

            // Add lights
            const color = 0xFFFFFF;
            const intensity = 1;
            const light = new THREE.DirectionalLight(color, intensity);
            light.position.set(-1, 2, 4);
            scene.add(light);

            // Light always visible
            const ambientColor = 0xFF000AA;
            const ambientIntensity = 1;
            const ambientLight = new THREE.AmbientLight(ambientColor, ambientIntensity);
            scene.add(ambientLight);

            // Add plane
            const planeSize = 40;
            const loader = new THREE.TextureLoader();
            const texture = loader.load('checker.png');
            texture.wrapS = THREE.RepeatWrapping;
            texture.wrapT = THREE.RepeatWrapping;
            texture.repeat.set(planeSize, planeSize);
            texture.magFilter = THREE.NearestFilter;
            texture.colorSpace = THREE.SRGBColorSpace;

            const planeGeo = new THREE.PlaneGeometry(planeSize, planeSize);
            const planeMat = new THREE.MeshBasicMaterial({ map: texture, side: THREE.DoubleSide });

            const planeMesh = new THREE.Mesh(planeGeo, planeMat);
            planeMesh.rotation.x = Math.PI * -.5;
            planeMesh.position.y = -5;
            scene.add(planeMesh);

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
                    const texture = new THREE.TextureLoader().load('wood.png');
                    const material = new THREE.MeshPhongMaterial({map: texture, depthTest: true, color: parseInt(cubeAttributes.color, 16)});
                    const cube = new THREE.Mesh(geometry, material);
                    cube.position.set(cubeAttributes.position.x, cubeAttributes.position.y, cubeAttributes.position.z);
                    cubes.push(cube);
                    scene.add(cube);
                }
            }

            createCube(numberOfCubes);

            // Initialize OrbitControls
            const controls = new OrbitControls(camera, renderer.domElement);
            controls.enableDamping = true; // Add smooth damping effect
            controls.dampingFactor = 0.05;
            controls.screenSpacePanning = false;
            controls.minDistance = 1;
            controls.maxDistance = 50;
            controls.maxPolarAngle = Math.PI / 2;

            function animateScene() {
                animationFrameId = requestAnimationFrame(animateScene);

                for (let i = 0; i < numberOfCubes; i++) {
                    cubes[i].rotation.x += 0.01;
                    cubes[i].rotation.y += 0.01;
                    cubes[i].rotation.z += 0.01;
                }

                controls.update(); // Update controls in the animation loop
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
                cancelAnimationFrame(animationFrameId)
                controls.dispose()
                renderer.dispose()
                containerRef.current?.removeChild(renderer.domElement)
            }
        })
    }, [])

    return <div ref={containerRef} style={{ width: '100%', height: '100vh' }} />
}