import { useState } from 'react'
import './App.css'

function App() {

  const [isOpen, setIsOpen] = useState(false)

  const openPopup = () => {
    setIsOpen(!isOpen)
  }

  return (
    <>

      <h1 className="text-center text-4xl font-bold mb-6">
        Dailu Planner
      </h1>


      <div className="flex justify-center">
        <button
          onClick={openPopup}
          className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition"
        >
          + Add Task
        </button>
      </div>


  {isOpen && (
  <div className="fixed inset-0 bg-black/40 flex justify-center items-center">

    <div className="relative bg-white w-[400px] h-[200px] rounded-xl shadow-lg p-6">

      <form className="flex flex-col gap-4">

         <p className='cursor-pointer' onClick={()=>setIsOpen(false)}>x</p>

        <input
          type="text"
          placeholder="Enter task"
          className="border border-gray-300 rounded-lg px-4 py-2 w-full"
        />

        <button
          type="submit"
          className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition"
        >
          Add Task
        </button>

      </form>

    </div>

  </div>
)}


 <div className="grid grid-cols-7 gap-4 pt-16 px-10">

  {["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"].map((day) => (
    <div 
      key={day}
      className="bg-green-500 rounded-xl min-h-[300px] p-4 shadow-lg"
    >
      <h2 className="text-center text-white font-bold text-lg mb-4">
        {day}
      </h2>

      <div className="bg-white rounded-lg p-3 text-gray-700 mb-2">
        Task example
      </div>

      <div className="bg-white rounded-lg p-3 text-gray-700">
        Another task
      </div>

    </div>
  ))}

</div>


 

    </>
  )
}

export default App