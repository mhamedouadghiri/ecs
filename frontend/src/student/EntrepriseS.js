import React, { useEffect, useState } from "react";
import Post from "./Post";
import "./EntrepriseS.css";
import { properties } from "../resources/properties";
function EntrepriseS({ user }) {
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
      {posts
        ? posts.map((post) => (
            <Post key={post.id} company={post} userId={user.id} />
          ))
        : "Loading data.."}
    </div>
  );
}

export default EntrepriseS;
