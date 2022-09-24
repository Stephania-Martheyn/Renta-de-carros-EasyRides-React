import React, { createContext, useState } from "react";

export const CategoriasContext = createContext();

export const CategoriasProvider = ({ children }) => {
  const [elegirCategorias, setElegirCategorias] = useState();
  return (
    <CategoriasContext.Provider value={{ elegirCategorias, setElegirCategorias }}>
      {children}
    </CategoriasContext.Provider>
  );
};
