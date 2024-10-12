/** @type {import('tailwindcss').Config} */
const colors = require('tailwindcss/colors')

module.exports = {
    content: ["src/**/*.{html,js}"],
    darkMode: false,
    theme: {
        screens: {
            sm: '480px',
            md: '768px',
            lg: '976px',
            xl: '1440px',
        },
        colors: {
            gray: colors.coolGray,
            blue: colors.lightBlue,
            red: colors.red,
            white: colors.white,
            yellow: colors.yellow,
            primary: {
                50: colors.red[50],
                100: colors.red[100],
                200: colors.red[200],
                300: colors.red[300],
                400: colors.red[400],
                500: colors.red[500],
                600: colors.red[600],
                700: colors.red[700],
                800: colors.red[800],
                900: colors.red[900],
            }
        },
        fontFamily: {
            sans: ['Graphik', 'sans-serif'],
            serif: ['Merriweather', 'serif'],
        },
        extend: {
            spacing: {
                '128': '32rem',
                '144': '36rem',
            },
            borderRadius: {
                '4xl': '2rem',
            }
        }
    },
    plugins: [],
}

