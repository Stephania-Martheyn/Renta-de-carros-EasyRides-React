import React, { createContext, useState } from "react";
export const FechasParaReservaContext = createContext();

export const FechasParaReservaProvider = ({ children }) => {
  const [fechaInicio, setFechaInicio] = useState();
  const [fechaFin, setFechaFin] = useState();
  return (
    <FechasParaReservaContext.Provider
      value={{ fechaInicio, setFechaInicio, fechaFin, setFechaFin }}
    >
      {children}
    </FechasParaReservaContext.Provider>
  );
};
