const API_BASE = 'http://localhost:8080/api/';

export const Delete = async(url: string) => {
  return await fetch(`${API_BASE}${url}`, {
    method: 'DELETE'
  });
}

export const Post = async(url: string, body: object) => {
  return await fetch(`${API_BASE}${url}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  });
}

export const Put = async(url: string, body: object) => {
  return await fetch(`${API_BASE}${url}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  });
}

export const Get = async(url: string) => {
  return await fetch(`${API_BASE}${url}`, {
    method: 'GET'
  });
}