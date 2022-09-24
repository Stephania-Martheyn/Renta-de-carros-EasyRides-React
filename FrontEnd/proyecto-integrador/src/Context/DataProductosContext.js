import React, { createContext, useState } from "react";
export const DataProductosContext = createContext();

export const DataProductosProvider = ({ children }) => {
    const [dataProductos, setDataProductos] = useState(false);

  return (
    <DataProductosContext.Provider value={{ dataProductos, setDataProductos }}>
      {children}
    </DataProductosContext.Provider>
  );
};
