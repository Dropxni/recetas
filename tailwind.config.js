/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/main/resources/templates/**/*.html',  // Ruta a tus plantillas Thymeleaf o HTML
    './src/main/resources/static/**/*.html'
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

