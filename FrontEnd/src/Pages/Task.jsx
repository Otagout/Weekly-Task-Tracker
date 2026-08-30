import { useState } from "react";
import axios from "../axios/api.js";
import { useEffect } from "react";
 



function Task() {
useEffect(() => {
  getTasks();
}, []);
  const [isOpen, setIsOpen] = useState(false);
  const [tasks, setTasks] = useState([]);
   const [task, setTask] = useState({
    title: "",
    description: "",
    status: "inProgress",
  });
  const getTasks=async()=>{
    try{
        const response = await axios.get("http://localhost:8080/api/tasks");
        setTasks(response.data);
        console.log("data", tasks.values);
    }catch(error){
      console.log(error)
    }
  }

  //function to handle delete task 

  const deleteTask=async(id)=>{
    try{
   const response = await axios.delete(`http://localhost:8080/api/tasks/${id}`);
   setTasks(tasks.filter((task)=>task.id !==id));
    }catch(error){
      console.log(error)
    }
  }
  // function to handle checkbox click 

const checked = async (id) => {

  const foundTask = tasks.find((task) => task.id === id);

  if (foundTask.status === "completed") {
    console.log("Task is already completed");
    return;
  }

  try {
    const response = await axios.put(
      `http://localhost:8080/api/tasks/${id}`,
      {
        ...foundTask,
        status: "completed",
      }
    );

    setTasks(
      tasks.map((task) =>
        task.id === id ? response.data : task
      )
    );

    console.log("data", response.data);

  } catch (error) {
    console.log(error);
  }
};
  const addTask = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/tasks",
        task
      );
      setTasks([...tasks, response.data])

      console.log("data", response.data);

      // Close popup after successfully creating task
      setIsOpen(false);

      // Reset form
      setTask({
        title: "",
        description: "",
        status: "inProgress",
      });

    } catch (error) {
      console.log(error);
    }
  };

  const openPopup = () => {
    setIsOpen(!isOpen);
  };

  return (
    <>
    
      <h1 className="text-center text-4xl font-bold mb-6">
        Daily Planner
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

          <div className="relative bg-white w-[400px] rounded-xl shadow-lg p-6">

            <form
              onSubmit={addTask}
              className="flex flex-col gap-4"
            >

              <p
                className="cursor-pointer"
                onClick={() => setIsOpen(false)}              >
                X
              </p>

              <input
                type="text"
                placeholder="Enter task"
                value={task.title}
                onChange={(e) =>
                  setTask({
                    ...task,
                    title: e.target.value
                  })
                }
                className="border border-gray-300 rounded-lg px-4 py-2 w-full"
              />

              <select
                value={task.status}
                onChange={(e) =>
                  setTask({
                    ...task,
                    status: e.target.value
                  })
                }
                className="border border-gray-300 rounded-lg px-4 py-2 w-full"
              >
                <option value="inProgress">In Progress</option>
                <option value="completed">Completed</option>
                <option value="todo">Todo</option>
              </select>

              <textarea
                placeholder="Enter task description"
                value={task.description}
                onChange={(e) =>
                  setTask({
                    ...task,
                    description: e.target.value
                  })
                }
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

<div className="flex flex-col items-center gap-4 bg-orange-100 py-10"> 
  {tasks?.map((task) => (
    <div
      key={task.id}
      className="flex flex-row items-center justify-between bg-white w-[300px] rounded-lg shadow-lg p-4"
    >
      <h2 className="text-xl font-bold">
        {task.title}
      </h2>

      <span>
        {task.status}
      </span>
        <button onClick={() => deleteTask(task.id)}>
  🗑️
      </button>
     
   <input
  type="checkbox"
  checked={task.status === "completed"}
  onChange={() => checked(task.id)}
  />
 
    </div>
  ))}

</div>
   
    </>
  );
}

export default Task;