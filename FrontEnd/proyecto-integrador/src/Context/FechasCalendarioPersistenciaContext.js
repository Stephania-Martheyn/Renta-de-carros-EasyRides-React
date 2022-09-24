import React, { createContext, useState } from "react";
export const FechasCalendarioPersistenciaContext = createContext();

export const FechasCalendarioPersistenciaProvider = ({ children }) => {

  const [fechasCalendarioPersistencia, setFechasCalendarioPersistencia] =
    useState([new Date(), new Date()]);

  return (
    <FechasCalendarioPersistenciaContext.Provider
      value={{ fechasCalendarioPersistencia, setFechasCalendarioPersistencia }}
    >
      {children}
    </FechasCalendarioPersistenciaContext.Provider>
  );
};
