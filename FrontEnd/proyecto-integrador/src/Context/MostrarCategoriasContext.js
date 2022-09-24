import React, { createContext, useState } from "react";

export const MostrarCategoriasContext = createContext();

export const MostrarCategoriasProvider = ({ children }) => {
  const [mostrarCategorias, setMostrarCategorias] = useState();
  return (
    <MostrarCategoriasContext.Provider
      value={{ mostrarCategorias, setMostrarCategorias }}
    >
      {children}
    </MostrarCategoriasContext.Provider>
  );
};
