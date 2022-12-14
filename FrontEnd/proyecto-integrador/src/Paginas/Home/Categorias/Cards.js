import React, { useContext } from "react";
import "./Cards.css";
import useFetch from "../../../Hooks/useFetch";
import { CategoriasContext } from "../../../Context/CategoriasContext";
import { MostrarCategoriasContext } from "../../../Context/MostrarCategoriasContext";
import { DataProductosContext } from "../../../Context/DataProductosContext";
export default function Cards() {
  const urlCategorias = "http://ec2-3-145-197-27.us-east-2.compute.amazonaws.com:8080/categorias";
  const { data } = useFetch(urlCategorias)
  const {  setElegirCategorias } = useContext(CategoriasContext);
  const {setMostrarCategorias } = useContext(MostrarCategoriasContext);
  const { setDataProductos } = useContext(DataProductosContext);
  
  
  return (
    <div>
      <h2 className="cardsCategoria-titulo">Busca por categorías</h2>
      <div className="cardsCategoria">
        {data &&
          data.categorias.map((cat) => (
            <div
              
              key={cat.id}
              className="cardsCategoria-unidad"
              onClick={
                () => {setMostrarCategorias(true) ;setElegirCategorias(cat.titulo); setDataProductos(false) }
              }
            >
              <img
                src={cat.url}
                alt=""
                className="cardsCategoria-unidad-img"
                
              />
              <p className="cardsCategoria-unidad-nombre">{cat.titulo}</p>
            </div>
          ))}
      </div>
    </div>
  );
}
