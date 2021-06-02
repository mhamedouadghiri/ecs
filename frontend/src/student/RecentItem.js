import React from "react";

function recentItem(props) {
  return (
    <div className="sidebar__recentItem">
      <span className="sidebar__hash">#</span>
      <p>{props.topic.name}</p>
    </div>
  );
}

export default recentItem;
