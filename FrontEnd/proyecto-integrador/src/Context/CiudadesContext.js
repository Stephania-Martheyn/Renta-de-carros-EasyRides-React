import React,{ createContext, useState } from "react";

export const CiudadesContext = createContext()

export const CiudadesProvider = ({children}) => {
    const [elegirCiudades, setElegirCiudades] = useState();
    return (
      <CiudadesContext.Provider value={{ elegirCiudades, setElegirCiudades }}>
        {children}
      </CiudadesContext.Provider>
    );
}