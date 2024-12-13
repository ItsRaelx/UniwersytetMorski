'use client'

import { useEffect, useRef, useState } from 'react'
import dynamic from 'next/dynamic'

// Dynamically import Three.js
// @ts-ignore
const THREE = dynamic(() => import('three'), { ssr: false })

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

      const geometry = new THREE.BoxGeometry()
      const material = new THREE.MeshBasicMaterial({ color: 0x00ff00, wireframe: true})
      const cube = new THREE.Mesh(geometry, material)
      scene.add(cube)

      const animate = () => {
        requestAnimationFrame(animate)
        cube.rotation.x += 0.01
        cube.rotation.y += 0.01
        renderer.render(scene, camera)
      }

      animate()

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

