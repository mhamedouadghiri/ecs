import React, { useEffect, useState } from "react";
import Post from "./Post";
import "./Entreprise.css";
import { properties } from "../resources/properties";
function Entreprise({ user }) {
  const [posts, setPosts] = useState(null);
  useEffect(() => {
    if (!posts) {
      fetch(`${properties.url}${properties.company}`)
        .then((res) => res.json())
        .then((data) => {
          setPosts(data);
          console.log(data);
        });
    }
  }, [posts]);
  return (
    <div className="entrepriseS">
      {posts ? (
        posts.map((post) => (
          <Post key={post.id} company={post} userId={user.id} />
        ))
      ) : (
        <div className="updating">No company insert</div>
      )}
    </div>
  );
}

export default Entreprise;
