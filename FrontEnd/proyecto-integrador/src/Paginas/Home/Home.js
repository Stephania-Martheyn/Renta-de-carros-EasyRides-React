import React from 'react'
import Header from './Header/Header'
import Buscador from './Buscador/Buscador'
import Cards from './Categorias/Cards'
import Listado  from './Listado/Listado'
import Footer from './Footer/Footer'

export const Home = () => {
  return (
    <div>
      <Buscador />
      <Cards />
      <Listado />
    </div>
  );
}
