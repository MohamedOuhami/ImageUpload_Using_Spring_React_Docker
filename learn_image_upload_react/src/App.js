import { useEffect, useState } from "react";
import axios from "axios";
import "./App.css"
function App() {
  const [imageDetails, setImageDetails] = useState(null);
  const [upload, setUpload] = useState(null);

  const fetchImages = async () => {
    const response = await axios.get("http://localhost:8080/images");
    setImageDetails(response.data);
    console.log(response.data);
  };

  const getImageData = async (image_name) => {
    const response = await axios.get(
      `http://localhost:8080/images/${image_name}`
    );
    return response.data;
  };

  useEffect(() => {
    fetchImages();
  }, []);

  const handleFileChange = (e) => {
    setUpload(e.target.files);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();

    for (let i = 0; i < upload.length; i++) {
      formData.append("images", upload[i]);
    }

    await axios.post("http://localhost:8080/images", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    fetchImages();
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this image?"
    );

    if (confirmDelete) {
      await axios.delete(`http://localhost:8080/images/${id}`);
      fetchImages();
    }
  };

  return (
    <div className="App">
      <h1>Fetching images</h1>

      <div className="imageForm">
        <h2>ImageForm</h2>
        <form encType="multipart/form-data">
          <input onChange={handleFileChange} multiple type="file" />
          <button onClick={handleSubmit}>Upload image</button>
        </form>
      </div>

      <div className="imageList">
        <h2>Image Listing</h2>
        {imageDetails ? (
          imageDetails.map((image, index) => (
            <div className="image_container" key={index}>
              <p>The image name is {image.name}</p>
              <button onClick={() => handleDelete(image.id)}>Delete image</button>
              <img
                width={100}
                height={100}
                src={`data:image/png;base64,` + image.image_data}
                alt={`Image ${index}`}
              ></img>
            </div>
          ))
        ) : (
          <p>No images available</p>
        )}
      </div>
    </div>
  );
}

export default App;
