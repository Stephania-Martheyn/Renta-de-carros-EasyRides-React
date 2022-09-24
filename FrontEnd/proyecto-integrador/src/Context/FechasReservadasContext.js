import React, { createContext, useState } from "react";
export const FechasReservadasContext = createContext();

export const FechasReservadasProvider = ({ children }) => {
  const [fechaInicio, setFechaInicio] = useState();
  const [fechaFin, setFechaFin] = useState();
  return (
    <FechasReservadasContext.Provider
      value={{ fechaInicio, setFechaInicio, fechaFin, setFechaFin }}
    >
      {children}
    </FechasReservadasContext.Provider>
  );
};
