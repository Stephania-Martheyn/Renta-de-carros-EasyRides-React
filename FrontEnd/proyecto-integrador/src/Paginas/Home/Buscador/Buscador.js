import React, { useContext, useState } from "react";
import "./Buscador.css";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";
import { SearchBar } from "./SearchBar";
import Calendario from "./Calendario";
import { DataProductosContext } from "../../../Context/DataProductosContext";


function  Buscador(){
  // creo un estado global que llegue a listado y ahi hago una condicional de si esta en falso muestro 
  // todo el listado y si cambia a true cambio a listado ciudades
  // aca traigo el estado de un context
  const {setDataProductos} = useContext(DataProductosContext);

  return (
    <div className="buscador">
      <h1 className="buscador-titulo">Buscá, reservá y <br></br> alquilá tu auto</h1>
      {/* <img src="https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80" /> */}
      <div className="buscadores">
        <SearchBar />
        <Calendario className="calendario" />
        <button
          className="buscador-submit"
          onClick={() => setDataProductos(true)}
        >
          Buscar
        </button>
      </div>
    </div>
  );
};

export default Buscador;

