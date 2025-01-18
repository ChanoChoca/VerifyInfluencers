// Definición para un tweet
interface Tweets {
  author_id: string;
  created_at: string;
  id: string;
  text: string;
  username: string;
}

// Definición para un lugar
interface Places {
  contained_within: string[];
  country: string;
  country_code: string;
  full_name: string;
  geo: {
    bbox: number[];
    geometry: {
      coordinates: number[];
      type: string;
    };
    properties: object;
    type: string;
  };
  id: string;
  name: string;
  place_type: string;
}

// Definición para un tema (topic)
interface Topics {
  description: string;
  id: string;
  name: string;
}

// Definición para una encuesta (poll)
interface Polls {
  duration_minutes: number;
  end_datetime: string;
  id: string;
  options: Array<{ label: string; position: number; votes: number }>;
  voting_status: string;
}

// Definición para un medio (media)
interface Media {
  height: number;
  media_key: string;
  type: string;
  width: number;
}

// Definición para un usuario
interface Users {
  created_at: string;
  id: string;
  name: string;
  protected: boolean;
  username: string;
}

// Respuesta general de la API (principal)
export interface TwitterApiResponse {
  data: Users; // Usuarios obtenidos
  errors?: Array<{ detail: string; status: number; title: string; type: string }>; // Errores
  includes: {
    media: Media[]; // Medios relacionados
    places: Places[]; // Lugares relacionados
    polls: Polls[]; // Encuestas relacionadas
    topics: Topics[]; // Temas relacionados
    tweets: Tweets[]; // Tweets relacionados
    users: Users[]; // Usuarios relacionados
  };
  meta: {
    next_token: string;
    previous_token: string;
  };
}
