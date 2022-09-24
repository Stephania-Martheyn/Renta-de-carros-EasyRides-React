import React, { createContext, useState } from "react";

export const DataPaginaProductosContext = createContext();

export const DataPaginaProductosProvider = ({ children }) => {
  const [elegirDataPaginaProductos, setElegirDataPaginaProductos] = useState();
  return (
    <DataPaginaProductosContext.Provider
      value={{ elegirDataPaginaProductos, setElegirDataPaginaProductos }}
    >
      {children}
    </DataPaginaProductosContext.Provider>
  );
};
