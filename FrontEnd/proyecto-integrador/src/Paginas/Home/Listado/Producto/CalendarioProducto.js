import React, { useContext, useEffect, useState } from "react";
import Calendar from "react-calendar";
import "@wojtekmaj/react-daterange-picker/dist/DateRangePicker.css";
import "react-calendar/dist/Calendar.css";
import "react-calendar/dist/Calendar.css";
import { FechasParaReservaContext } from "../../../../Context/FechasParaReservaContext";
import { FechasCalendarioPersistenciaContext } from "../../../../Context/FechasCalendarioPersistenciaContext";
import dayjs from "dayjs";
import { useParams } from "react-router";
import useFetch from "../../../../Hooks/useFetch";

function CalendarioProducto() {
  // const [value, onChange] = useState([new Date(), new Date()]);
  const { fechaInicio, setFechaInicio } = useContext(FechasParaReservaContext);
  const { fechaFin, setFechaFin } = useContext(FechasParaReservaContext);
  const { fechasCalendarioPersistencia, setFechasCalendarioPersistencia } = useContext(FechasCalendarioPersistenciaContext);
  const [fechaInicioDisabled,setFechaInicioDisabled] = useState()
  const { id } = useParams();
  const urlReservas =
    "http://ec2-3-145-197-27.us-east-2.compute.amazonaws.com:8080/productos/"+id+"/reservas";  
  const {data} = useFetch(urlReservas)
  const disabledDates = [(fechaInicio)];
  // const disabledDates = [(dayjs(fechaInicio).format("YYYY,MM,DD"))];
  // const disabledDates = fechaInicio
  return (
    <div className="calendarioInteractivo">
      {console.log(fechaInicio)}
      {console.log(disabledDates)}
      {useEffect(() => {
        data &&
          data.productos.map((prod) => (
            <>{setFechaInicioDisabled(prod.fechaInicial)}</>
          ));
      }, [])}

      <>
        <Calendar
          onChange={setFechasCalendarioPersistencia}
          value={fechasCalendarioPersistencia}
          minDate={new Date()}
          showDoubleView={true}
          selectRange={true}
          // tileDisabled={({ date, view }) =>
          //   view === "month" && // Block day tiles only
          //   disabledDates.some(
          //     (disabledDate) =>
          //       date.getFullYear() === disabledDate.getFullYear() &&
          //       date.getMonth() === disabledDate.getMonth() &&
          //       date.getDate() === disabledDate.getDate()
          //   )
          // }
        />
      </>

      {setFechaInicio(
        dayjs(fechasCalendarioPersistencia[0]).format("YYYY-MM-DD")
      )}
      {setFechaFin(dayjs(fechasCalendarioPersistencia[1]).format("YYYY-MM-DD"))}
    </div>
  );
}

export default CalendarioProducto;